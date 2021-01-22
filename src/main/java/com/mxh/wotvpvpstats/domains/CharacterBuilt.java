package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_character_built")
@Builder
public class CharacterBuilt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHB_ID")
    private Long id;

    @Column(name = "CHB_NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "CHB_CTR_ID", referencedColumnName = "CTR_ID")
    private Character character;

    @OneToOne
    @JoinColumn(name = "CHB_USR_ID", referencedColumnName = "USR_ID")
    private User user;
}
