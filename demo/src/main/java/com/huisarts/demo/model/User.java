package com.huisarts.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

        @Id
        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false, length = 255)
        private String password;

        @Column
        private boolean enabled = true;

        @Column
        private String apikey;


        //Een user kan meerdere Authorities hebben
        @OneToMany(
                targetEntity = Autorisatie.class,
                mappedBy = "username",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER)
        //EAGER betekend dat het meteen uit de database haalt
        private Set<Autorisatie> autorisaties = new HashSet<>();


        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getApikey() { return apikey; }
        public void setApikey(String apikey) { this.apikey =apikey; }

        public Set<Autorisatie> getAutorisaties() { return autorisaties; }
        public void addAutorisatie(Autorisatie autorisatie) {
                this.autorisaties.add(autorisatie);
        }
        public void removeAutorisatie(Autorisatie autorisatie) {
                this.autorisaties.remove(autorisatie);
        }

}
