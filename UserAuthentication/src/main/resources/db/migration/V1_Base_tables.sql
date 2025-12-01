CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	is_active BOOLEAN NOT NULL DEFAULT true,
	last_login_at TIMESTAMP WITH TIME ZONE,
	account_locked BOOLEAN NOT NULL DEFAULT false,
	account_locked_at TIMESTAMP WITH TIME ZONE,
	verification_email_token VARCHAR(255),
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP WITH TIME ZONE
);
CREATE INDEX indx_users_username ON users(username);
CREATE INDEX indx_users_active ON users(is_active) WHERE is_active=true;


CREATE TABLE user_roles(
	user_id BIGINT NOT NULL,
	role VARCHAR(255) NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	created_by BIGINT,
	PRIMARY KEY (user_id, role),
	FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fk_valid_role CHECK (role IN ('ADMIN', 'MANAGER', 'EMPLOYEE','GUEST'))
);
CREATE INDEX indx_user_roles_userid ON user_roles(user_id);
CREATE INDEX indx_user_roles_role ON user_roles(role);


CREATE TABLE employees(
	id BIGSERIAL PRIMARY KEY,
	email VARCHAR(255) NOT NULL UNIQUE,
	full_name VARCHAR(255) NOT NULL,
	gender VARCHAR(255)  DEFAULT  'Others',
	date_of_birth DATE,
	mobile_no VARCHAR(255) NOT NULL,
	address VARCHAR(255),
	nationality VARCHAR(255),
	marital_status VARCHAR(255) NOT NULL,
	department VARCHAR(255) NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP WITH TIME ZONE
)
CREATE INDEX indx_employees_email ON employees(email);
CREATE INDEX indx_employees_mobile_no ON employees(mobile_no);
CREATE INDEX indx_employees_department ON employees(department);


CREATE TABLE companies{
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	company_size VARCHAR(255),
	mobile_no VARCHAR(255),
	email VARCHAR(255) NOT NULL UNIQUE,
	address VARCHAR(255),
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP WITH TIME ZONE
}
CREATE INDEX indx_companies_name ON companies(name);















