package com.thoughtworks.librarysys;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginMenuItemTest {
    @Test
    public void shouldLoginAValidUser() {
        Users users = mock(Users.class);
        when(users.authenticate("111-1111", "password1")).thenReturn(new User("111-1111", "password1", "Registered"));
        ConsoleView consoleView = mock(ConsoleView.class);
        when(consoleView.inputFromUser()).thenReturn("111-1111", "password1");
        LoginMenuItem loginMenuItem = new LoginMenuItem(users, consoleView);

        loginMenuItem.performOperation();

        verify(consoleView).printOnConsole("Enter Library ID : ");
        verify(consoleView).printOnConsole("Enter Password : ");
        verify(users).authenticate("111-1111", "password1");
    }
}