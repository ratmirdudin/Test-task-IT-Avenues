package com.ratmirdudin.userservice.domains.models;

import com.ratmirdudin.userservice.domains.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_sequence")
    @SequenceGenerator(name = "app_user_sequence", allocationSize = 5)
    @Column(name = "app_user_id")
    private Long id;

    @Column(name = "app_user_name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "app_user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "app_user_image_uri", nullable = false)
    private String imageUri;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_user_status", columnDefinition = "varchar(7) default 'Offline'")
    private Status status;
}
