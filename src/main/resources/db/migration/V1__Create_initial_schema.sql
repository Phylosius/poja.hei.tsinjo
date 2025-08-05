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
