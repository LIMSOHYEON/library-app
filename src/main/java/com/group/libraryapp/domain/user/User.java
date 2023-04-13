package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.loan.UserLoanHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false, length=25, name = "name") // name varchar(20)
    private String name;
    private Integer age;

    // 여러개의 대출 기록이 있기때문에 list 표현
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name, Integer age) {

        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName)) // 같은걸
                .findFirst()// 찾아서
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn(); // 반납처리
    }
}
