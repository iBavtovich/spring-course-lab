package lab.model.simple;

import lab.model.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleContact implements Contact {
    String type;
    String value;

    SimpleContact(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public static SimpleContactBuilder builder() {
        return new SimpleContactBuilder();
    }

    public static class SimpleContactBuilder {
        private String type;
        private String value;

        SimpleContactBuilder() {
        }

        public SimpleContactBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SimpleContactBuilder value(String value) {
            this.value = value;
            return this;
        }

        public SimpleContact build() {
            return new SimpleContact(type, value);
        }

        public String toString() {
            return "SimpleContact.SimpleContactBuilder(type=" + this.type + ", value=" + this.value + ")";
        }
    }
}
