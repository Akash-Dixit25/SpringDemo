package com.prototype.SpringPrototype.model;





import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "tbl_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserData {

		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private int id;
	    @Getter
		@NotBlank(message = "Invalid User Name: Empty User Name")
	    @NotNull(message = "Invalid User Name: User Name is NULL")
	    @Size(min = 3, max = 30, message = "Invalid User Name: Must be of 3 - 30 characters")
	    private String userName;
	    @Email(message = "Invalid email")
	    private String email;
	    @NotBlank(message = "Invalid Phone number: Empty number")
	    @NotNull(message = "Invalid Phone number: Number is NULL")
	    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
	    private String mobile;
	    @Getter
		@NotBlank(message = "Invalid Password: Empty Password")
	    @NotNull(message = "Invalid Password: Password is NULL")
	    @Size(min = 5, max = 30, message = "Invalid Password: Must be of 5 - 30 characters")
	    private String password;
	    @Getter
		@NotBlank(message = "Invalid Role: Empty Role")
	    @NotNull(message = "Invalid Role: Role is NULL")
	    private String role; 
	    @Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero")
	    @Max(value = 100, message = "Invalid Age: Exceeds 100 years")
	    private Integer age;

		

	}