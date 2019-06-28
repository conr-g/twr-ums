package controllers;

import api.post.UserHandler;
import model.UserInput;
import dao.InMemoryUserRepository;
import dao.UserRepository;
import model.User;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import static org.mockito.Mockito.*;

public class UserControllerTest extends WithApplication {
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void shouldSaveUser() {
        UserInput userInput = UserInput.builder()
                .username("chief")
                .password("somehashedpassword")
                .build();
        User user = User.builder()
                .username("chief")
                .password("somehashedpassword")
                .build();

        UserRepository repository = mock(InMemoryUserRepository.class);
        UserHandler handler = new UserHandler(repository);
        handler.create(
                userInput
        );

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(repository, times(1)).save(argumentCaptor.capture());
        Assert.assertEquals(user.getUsername(), argumentCaptor.getValue().getUsername());
    }

    @Test
    public void shouldLoginUser() {
        UserInput userInput = UserInput.builder()
                .username("chief")
                .password("somehashedpassword")
                .build();
        User user = User.builder()
                .username("chief")
                .password("somehashedpassword")
                .build();

        UserRepository repository = mock(InMemoryUserRepository.class);
        UserHandler handler = new UserHandler(repository);
        handler.login(userInput);

        verify(repository, times(1)).loginUser(userInput);
        Assert.assertEquals(user.getUsername(), handler.login(userInput).get().getUsername());
    }
}
