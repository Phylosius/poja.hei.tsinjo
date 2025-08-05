package school.hei.tsinjo.endpoint.rest.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import school.hei.tsinjo.endpoint.rest.controller.dto.DonationForm;
import school.hei.tsinjo.endpoint.rest.controller.dto.TransactionDto;
import school.hei.tsinjo.model.Donation;
import school.hei.tsinjo.model.Help;
import school.hei.tsinjo.repository.DonationRepository;
import school.hei.tsinjo.service.DonationService;
import school.hei.tsinjo.service.HelpService;

@Controller
@AllArgsConstructor
public class TsinjoController {

  private final DonationRepository donationRepository;
  private final HelpService helpService;
  private final DonationService donationService;

  @GetMapping("/")
  public String index(Model model) {
    List<Donation> donations = donationRepository.findAll();
    List<Help> helps = helpService.findAll();

    List<TransactionDto> transactions = new ArrayList<>();

    donations.forEach(
        d ->
            transactions.add(
                TransactionDto.builder()
                    .type("Donation")
                    .personEmail(d.getDonor().getEmail())
                    .date(d.getCreationTimestamp())
                    .status(d.getPayment().getStatus().toString())
                    .build()));

    helps.forEach(
        h ->
            transactions.add(
                TransactionDto.builder()
                    .type("Help")
                    .personEmail(h.getBeneficiary().getEmail())
                    .date(h.getCreationTimestamp())
                    .description(h.getDescription())
                    .build()));

    transactions.sort(Comparator.comparing(TransactionDto::getDate).reversed());

    model.addAttribute("transactions", transactions);
    model.addAttribute("donationForm", new DonationForm());
    return "tsinjo";
  }

  @PostMapping("/donations")
  public String createDonation(@ModelAttribute DonationForm donationForm) {
    donationService.createDonation(donationForm);
    return "redirect:/";
  }
}
