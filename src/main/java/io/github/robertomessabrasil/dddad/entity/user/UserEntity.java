package io.github.robertomessabrasil.dddad.entity.user;

import io.github.robertomessabrasil.dddad.entity.user.event.UserValidationCode;
import io.github.robertomessabrasil.dddad.entity.user.event.UserValidationEvent;
import io.github.robertomessabrasil.dddad.repository.IUserRepository;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

public class UserEntity {

    private int id;
    private String name;
    private String email;
    private UserRoleVO role;

    public int getId() {
        return id;
    }

    public UserEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoleVO getRole() {
        return role;
    }

    public UserEntity setRole(UserRoleVO role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void validate(EventObserver eventObserver) throws InterruptException {

        if ((this.name == null) || (this.name.length() < 3)) {
            UserValidationEvent userValidationEvent = new UserValidationEvent();
            userValidationEvent.setUser(this);
            userValidationEvent.setCode(UserValidationCode.INVALID_NAME);
            eventObserver.notify(userValidationEvent);
        }

        if ((this.email == null) || (this.email.length() < 3)) {
            UserValidationEvent userValidationEvent = new UserValidationEvent();
            userValidationEvent.setUser(this);
            userValidationEvent.setCode(UserValidationCode.INVALID_EMAIL);
            eventObserver.notify(userValidationEvent);
        }

    }
}
