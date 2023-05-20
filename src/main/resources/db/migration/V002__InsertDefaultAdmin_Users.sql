INSERT INTO users (user_id, first_name, last_name, email, phone, pwd, profile_img, background_img, created_at, enabled, role, gender)
VALUES (
  '6d8a1dbf-437d-4f39-9525-1901dc1846f1', -- user_id
  'ADMIN', -- first_name
  '', -- last_name
  'admin@gmail.com', -- email
  '', -- phone
  '$2a$10$9xaZYB/e.TZNn8gvFIm5o.BRjhfVXAzo0TWqVfdulDsOdYIWF1mmu', -- pwd (replace with the actual hashed password)
  '', -- profile_img (replace with the actual image URL)
  '', -- background_img (replace with the actual image URL)
  current_timestamp, -- created_at (current timestamp)
  true, -- enabled
  'ROLE_ADMIN', -- role
  'MALE' -- gender
);
