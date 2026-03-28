/**
 * 
 */
package App;

import javax.swing.UIManager;
import vista.LoginView; // Importa la ventana de login desde el paquete vista

/**
 * Clase principal de la aplicación
 * Se encarga de iniciar la interfaz gráfica
 */
public class Main {

    public static void main(String[] args) {
        
        // ===== Configurar Look & Feel =====
        try {
            // Recorre los LookAndFeel instalados en el sistema
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { // Busca el Look & Feel "Nimbus"
                    UIManager.setLookAndFeel(info.getClassName()); // Aplica Nimbus
                    break; // Sale del bucle después de aplicar Nimbus
                }
            }
        } catch (Exception e) {
            // Si ocurre algún error al aplicar Nimbus, imprime la traza
            e.printStackTrace();
        }

        // ===== Abrir ventana de Login =====
        new LoginView().setVisible(true); // Muestra la ventana de inicio de sesión
    }
}

