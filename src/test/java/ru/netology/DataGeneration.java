package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGeneration {

    public DataGeneration() {
    }

    public static class Registration {

        private Registration() {
        }

        public static InputFields generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new InputFields(faker.address().city(), faker.name().fullName(), faker.numerify("+7##########"));
        }
    }

    public static String generateDate(int date) {
        return LocalDate.now().plusDays(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


}
