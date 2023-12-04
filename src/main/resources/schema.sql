CREATE TABLE IF NOT EXISTS weather (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date_time DATETIME NOT NULL,
    temperature DECIMAL(5, 2) NOT NULL,
    wind_mph DECIMAL(5, 2) NOT NULL,
    pressure_mb DECIMAL(6, 2) NOT NULL,
    humidity TINYINT NOT NULL,
    weather_condition VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);