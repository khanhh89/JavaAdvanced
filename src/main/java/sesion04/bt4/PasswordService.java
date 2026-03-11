package sesion04.bt4;

public class PasswordService {
    public String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Yeu";
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (c != ' ' && !Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        // Kiem tra tieu chi Manh
        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Manh";
        }

        // Neu co du 8 ky tu nhung thieu 1 trong cac loai ky tu thi la Trung binh
        // Ngoai tru truong hop chi co mot loai ky tu duy nhat (nhu chi co chu thuong)
        int criteriaMet = 0;
        if (hasUpper) criteriaMet++;
        if (hasLower) criteriaMet++;
        if (hasDigit) criteriaMet++;
        if (hasSpecial) criteriaMet++;

        if (criteriaMet >= 2) {
            return "Trung binh";
        }

        return "Yeu";
    }
}