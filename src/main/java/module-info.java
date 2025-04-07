module co.edu.uniquindio.poo.BilleteraVirtualFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens co.edu.uniquindio.poo.billeteravirtualfx to javafx.fxml;

    exports co.edu.uniquindio.poo.billeteravirtualfx;
    exports co.edu.uniquindio.poo.billeteravirtualfx.modelo;
    exports co.edu.uniquindio.poo.billeteravirtualfx.controladores;
    opens co.edu.uniquindio.poo.billeteravirtualfx.controladores to javafx.fxml;
}
