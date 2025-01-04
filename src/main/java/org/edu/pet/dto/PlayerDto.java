package org.edu.pet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edu.pet.validation.hibernate.CorrectName;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlayerDto {

    @NotBlank(message = "Player name cannot be blank")
    @Size(max = 64, message = "Name length cannot be lower 0 and greater {max}(incl.)")
    @CorrectName
    private String name;
}