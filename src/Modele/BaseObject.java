package Modele;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e8c39b43-6bdb-4eca-92a7-cef4de79c5d8")
public abstract class BaseObject {
    @objid ("01495af3-2c0f-466c-b102-cf2440edd652")
    protected int column;

    @objid ("d8b954f6-9d2d-4d83-8ddc-712db2f15e7a")
    protected int row;

    @objid ("60ec0d79-00fa-4a63-a11e-d3481a8616b9")
    public int getColumn() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.column;
    }

    @objid ("7444b450-8ff7-47a5-a7a4-cbfc0cb1155c")
    public int getRow() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.row;
    }

}
