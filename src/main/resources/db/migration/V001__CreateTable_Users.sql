CREATE TABLE users (
  user_id UUID PRIMARY KEY,
  first_name VARCHAR(16) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  email VARCHAR(320) NOT NULL,
  pwd VARCHAR(255) NOT NULL,
  "role" VARCHAR(16) NOT NULL CHECK (
    "role" = 'ROLE_STUDENT'
    OR "role" = 'ROLE_TEACHER'
    OR "role" = 'ROLE_ADMIN'
  ),
  gender VARCHAR(12) NOT NULL CHECK (
    "gender" = 'MALE'
    OR "gender" = 'FEMALE'
    OR "gender" = 'OTHER'
  ),
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  "enabled" BOOLEAN NOT NULL DEFAULT true
);