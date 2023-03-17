CREATE TYPE plec AS ENUM ('MALE', 'FEMALE');
CREATE TABLE user_data (
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           profile_image VARCHAR(255),
                           street VARCHAR(255) NOT NULL,
                           city VARCHAR(255) NOT NULL,
                           postal_code VARCHAR(255) NOT NULL,
                           date_of_birth VARCHAR(255) NOT NULL,
                           gender plec NOT NULL,
                           accept_terms_and_conditions BOOLEAN NOT NULL,
                           referral_source VARCHAR(255),
                           zapis_image BYTEA
);