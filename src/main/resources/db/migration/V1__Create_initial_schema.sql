CREATE TABLE payment (
    id VARCHAR(255) PRIMARY KEY,
    psp_payment_id VARCHAR(255),
    amount INT NOT NULL,
    creation_timestamp TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    method VARCHAR(255) NOT NULL
);

CREATE TABLE donor (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE beneficiary (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE donation (
    id VARCHAR(255) PRIMARY KEY,
    donor_id VARCHAR(255) NOT NULL,
    payment_id VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (donor_id) REFERENCES donor(id),
    FOREIGN KEY (payment_id) REFERENCES payment(id)
);

CREATE TABLE help (
    id VARCHAR(255) PRIMARY KEY,
    beneficiary_id VARCHAR(255) NOT NULL,
    payment_id VARCHAR(255) NOT NULL,
    description TEXT,
    creation_timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (beneficiary_id) REFERENCES beneficiary(id),
    FOREIGN KEY (payment_id) REFERENCES payment(id)
);

-- Preprod data

-- Donation
INSERT INTO donor (id, email, full_name) VALUES ('preprod-donor-1', 'donor.one@example.com', 'Donor One');
INSERT INTO payment (id, psp_payment_id, amount, creation_timestamp, status, method) VALUES ('preprod-payment-1', 'psp-preprod-1', 10000, '2024-05-20 10:00:00', 'SUCCEEDED', 'ORANGE_MONEY');
INSERT INTO donation (id, donor_id, payment_id, creation_timestamp) VALUES ('preprod-donation-1', 'preprod-donor-1', 'preprod-payment-1', '2024-05-20 10:00:00');

-- Help
INSERT INTO beneficiary (id, email, full_name) VALUES ('preprod-beneficiary-1', 'beneficiary.one@example.com', 'Beneficiary One');
INSERT INTO payment (id, psp_payment_id, amount, creation_timestamp, status, method) VALUES ('preprod-payment-2', NULL, -5000, '2024-05-21 11:00:00', 'SUCCEEDED', 'ORANGE_MONEY');
INSERT INTO help (id, beneficiary_id, payment_id, description, creation_timestamp) VALUES ('preprod-help-1', 'preprod-beneficiary-1', 'preprod-payment-2', 'Accident description for preprod.', '2024-05-21 11:00:00');
