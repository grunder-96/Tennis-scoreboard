package org.edu.pet.model.match;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegularPoints extends Points {

     @Override
     public int next() {
          return RegularPointEnum.findByPointValue(current())
                  .getNext()
                  .getPointValue();
     }

     @Override
     public String view() {
          return RegularPointEnum.findByPointValue(current())
                  .getView();
     }

     public RegularPoints(int value) {
          super(value);
     }
}