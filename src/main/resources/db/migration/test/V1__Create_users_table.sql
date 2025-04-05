-- DROP SCHEMA sch_security;

CREATE SCHEMA if not exists sch_security AUTHORIZATION usr_bci;
SET SCHEMA sch_security;

-- sch_security.tbl_users definition

-- Drop table

-- DROP TABLE sch_security.tbl_users;

CREATE TABLE sch_security.tbl_users (
    id uuid NOT NULL,
    "name" varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    "password" varchar(255) NOT NULL,
    created timestamp NULL,
    modified timestamp NULL,
    last_login timestamp NULL,
    "token" varchar(512) NULL,
    is_active bool NOT NULL DEFAULT true,
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
CREATE INDEX idx_users_email ON sch_security.tbl_users USING btree (email);
CREATE INDEX idx_users_token ON sch_security.tbl_users USING btree (token);

-- Permissions

ALTER TABLE sch_security.tbl_users OWNER TO usr_bci;
GRANT ALL ON TABLE sch_security.tbl_users TO usr_bci;


-- sch_security.tbl_user_phones definition

-- Drop table

-- DROP TABLE sch_security.tbl_user_phones;

CREATE TABLE sch_security.tbl_user_phones (
    id uuid NOT NULL,
    "number" varchar(20) NOT NULL,
    city_code varchar(10) NOT NULL,
    country_code varchar(10) NOT NULL,
    user_id uuid NOT NULL,
    created timestamp NULL,
    modified timestamp NULL,
    CONSTRAINT tbl_user_phones_pkey PRIMARY KEY (id),
    CONSTRAINT uni_tbl_user_phones_number_city_code_user_id UNIQUE (number, city_code, country_code, user_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES sch_security.tbl_users(id) ON DELETE CASCADE
);

-- Permissions

ALTER TABLE sch_security.tbl_user_phones OWNER TO usr_bci;
GRANT ALL ON TABLE sch_security.tbl_user_phones TO usr_bci;