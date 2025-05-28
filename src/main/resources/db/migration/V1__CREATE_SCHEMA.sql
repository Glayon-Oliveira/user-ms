CREATE TYPE user_status AS ENUM ('ACTIVE', 'DESACTIVED', 'PENDING', 'SUSPENDED');
CREATE TYPE user_role AS ENUM ('COMUM', 'MANAGER', 'ADMIN');

CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	status user_status NOT NULL DEFAULT 'ACTIVE',
	role user_role NOT NULL DEFAULT 'COMUM',
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE profiles(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
	name VARCHAR(255) NOT NULL,
	tel VARCHAR(20) UNIQUE,
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE addresses(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
	nation VARCHAR(2) NOT NULL,
	state VARCHAR(2) NOT NULL,
	city VARCHAR(255) NOT NULL,
	district VARCHAR(255) NOT NULL,
	street_name VARCHAR(255) NOT NULL,
	street_number VARCHAR(20) NOT NULL,
	complement VARCHAR(255),
	postal_code VARCHAR(20),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_users_status ON users(status);
CREATE INDEX idx_users_role ON users(role);

CREATE INDEX idx_addresses_nation_state on addresses(nation, state);
CREATE INDEX idx_addresses_city ON addresses(city);
