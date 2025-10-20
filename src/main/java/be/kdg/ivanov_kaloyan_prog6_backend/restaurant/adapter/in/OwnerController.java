package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.RegisterOwnerCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.RegisterOwnerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final RegisterOwnerUseCase registerOwnerUseCase;

    public OwnerController(final RegisterOwnerUseCase registerOwnerUseCase) {
        this.registerOwnerUseCase = registerOwnerUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> register(@AuthenticationPrincipal Jwt jwt){
        final RegisterOwnerCommand command = new RegisterOwnerCommand(UUID.fromString(jwt.getSubject()),
           jwt.getClaimAsString("given_name"),
           jwt.getClaimAsString("family_name"),
           jwt.getClaimAsString("email")
        ) ;

        this.registerOwnerUseCase.register(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
