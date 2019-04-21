package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "jpa"})
public class JPAMealServiceTest extends AbstractMealServiceTest {
}
