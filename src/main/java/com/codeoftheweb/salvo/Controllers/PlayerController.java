package com.codeoftheweb.salvo.Controllers;
import com.codeoftheweb.salvo.Modelo.*;
import com.codeoftheweb.salvo.Modelo.GamePlayer;
import com.codeoftheweb.salvo.Repositories.*;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api")
public class PlayerController {
}
