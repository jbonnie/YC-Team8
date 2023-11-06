package yc.team8.baseball.user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.team8.baseball.user.domain.User;
import yc.team8.baseball.user.dto.UserDto;
import yc.team8.baseball.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 새로운 유저 생성 후 DB에 저장
    public User create(UserDto userDto) {
        User userEntity = userDto.toEntity();
        User saved = userRepository.save(userEntity);
        return saved;
    }

    // 로그인 정보를 받고 유저 검증
    public User checkUser(UserDto userDto) {
        if(userDto == null)
            return null;
        User user = userRepository.findByLoginIdAndPassword(userDto.getLoginId(), userDto.getPassword()).orElse(null);
        return user;
    }

    // 회원 id를 통해 DB에서 User entity 가져오기
    public User getUser(Long id) {
        User target = userRepository.findById(id).orElse(null);
        return target;
    }

    // 회원 정보 수정하기
    public User update(UserDto userDto) {
        // 1. DTO -> Entity
        User userEntity = userDto.toEntity();
        // 2. 기존의 유저 정보 가져오기
        User target = userRepository.findById(userEntity.getId()).orElse(null);
        // 3. 기존 데이터 값 갱신
        if(target != null) {
            userRepository.save(userEntity);
        }
        return userEntity;
    }

    // 회원 탈퇴
    public void delete(Long id) {
        User target = userRepository.findById(id).orElse(null);
        if(target != null)
            userRepository.delete(target);      // 유저 정보 삭제
    }
}
