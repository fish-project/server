package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.model.user;
import src.repository.userRepository;

@Service
public class GetUserService {
    @Autowired
    private userRepository userRepository;

    public Page<user> getUsers(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAllBy(pageable);
    }
}
