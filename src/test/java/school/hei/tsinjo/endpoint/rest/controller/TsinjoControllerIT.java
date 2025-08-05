package school.hei.tsinjo.endpoint.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import school.hei.tsinjo.conf.FacadeIT;
import school.hei.tsinjo.service.DonationService;

@AutoConfigureMockMvc
class TsinjoControllerIT extends FacadeIT {

  @Autowired private MockMvc mockMvc;

  @MockBean private DonationService donationService;

  @Test
  void getIndex() throws Exception {
    mockMvc
        .perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("tsinjo"))
        .andExpect(model().attributeExists("transactions"))
        .andExpect(model().attributeExists("donationForm"));
  }

  @Test
  void createDonation() throws Exception {
    mockMvc
        .perform(
            post("/donations")
                .param("fullName", "Test Donor")
                .param("email", "test@example.com")
                .param("amount", "10000")
                .param("pspPaymentId", "psp-123"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/"));
  }
}
