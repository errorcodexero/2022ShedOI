package frc.robot;

import org.xero1425.base.subsystems.oi.OIPanel;
import org.xero1425.base.subsystems.oi.OISubsystem;
import org.xero1425.base.subsystems.oi.OILed.State;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.ISettingsSupplier;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;
import org.xero1425.base.actions.InvalidActionRequest;

public class ShedOIDevice extends OIPanel {

    public ShedOIDevice(OISubsystem parent, String name, int index) throws BadParameterTypeException, MissingParameterException {
        super(parent, name, index) ;

        initializeGadgets() ;

        ISettingsSupplier settings = parent.getRobot().getSettingsSupplier() ;
    }

    @Override
    public void createStaticActions() throws Exception {
    }

    @Override
    public void generateActions() throws InvalidActionRequest {
    }


    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num ;

       // num = getSubsystem().getSettingsValue("panel:gadgets:shoot_collect_mode").getInteger();
       // collect_v_shoot_gadget_ = mapButton(num, OIPanelButton.ButtonType.Level);

    }
}