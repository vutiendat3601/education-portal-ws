CREATE TABLE users (
  user_id UUID PRIMARY KEY,
  first_name VARCHAR(16) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  email VARCHAR(320) NOT NULL,
  pwd VARCHAR(128) NOT NULL,
  "role" VARCHAR(12) NOT NULL CHECK (
    "role" = 'STUDENT'
    OR "role" = 'TEACHER'
    OR "role" = 'ADMIN'
  ),
  gender VARCHAR(12) NOT NULL CHECK (
    "gender" = 'MALE'
    OR "gender" = 'FEMALE'
    OR "gender" = 'OTHER'
  ),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  non_locked BOOLEAN NOT NULL DEFAULT true,
  non_expired BOOLEAN NOT NULL DEFAULT true,
  credentials_non_expired BOOLEAN NOT NULL DEFAULT true,
  "enabled" BOOLEAN NOT NULL DEFAULT true
);