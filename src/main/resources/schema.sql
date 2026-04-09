CREATE TABLE IF NOT EXISTS department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS production_line (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    line VARCHAR(255),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

CREATE TABLE IF NOT EXISTS production_unit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    unit VARCHAR(255),
    production_line_id BIGINT,
    FOREIGN KEY (production_line_id) REFERENCES production_line(id)
);