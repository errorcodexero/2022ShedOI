package frc.robot;

import org.xero1425.base.actions.Action;
import org.xero1425.base.subsystems.motorsubsystem.MotorEncoderGotoAction;
import org.xero1425.base.subsystems.motorsubsystem.MotorPowerAction;
import org.xero1425.base.subsystems.motorsubsystem.MotorSubsystem;
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
    private MotorPowerAction motor1PowerAction;
    private MotorPowerAction motor2PowerAction;
    private int motor1_;
    private int motor2_;

    public ShedOIDevice(OISubsystem parent, String name, int index) throws BadParameterTypeException, MissingParameterException {
        super(parent, name, index) ;

        initializeGadgets() ;

        ISettingsSupplier settings = parent.getRobot().getSettingsSupplier() ;
    }

    @Override
    public void createStaticActions() throws Exception {
        var robotSubsystem = (ShedOIRobotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
        var motor1Subsystem = robotSubsystem.getMotor1Subsystem();
        var motor2Subsystem = robotSubsystem.getMotor2Subsystem();

        motor1OffAction = new MotorPowerAction(motor1Subsystem, 0);
        motor2OffAction = new MotorPowerAction(motor2Subsystem, 0);

        //TODO: value from the knob, not just 0.2
        motor1PowerAction = new MotorPowerAction(motor1Subsystem, 0.2);
        motor2PowerAction = new MotorPowerAction(motor2Subsystem, 0.2);

    }

    @Override
    public void generateActions() throws InvalidActionRequest {

        //motor 1 actions
        if (getValue(motor1_) == 0) {
            motor1Subsystem.setAction(motor1POffAction) ;         
        }
        if (getValue(motor1_) == 1) {
            motor1Subsystem.setAction(motor1PowerAction) ;       
        }

        //motor 2 actions
        if (getValue(motor2_) == 0) {
            motor1Subsystem.setAction(motor1POffAction) ; 
        }
        if {
            motor1Subsystem.setAction(motor1PowerAction) ;       
        }

    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num ;
        
        //TODO: add motor1 and motor2 to jsonc file
        num = getSubsystem().getSettingsValue("panel:gadgets:motor1").getInteger();
        motor1_ = mapButton(num, OIPanelButton.ButtonType.Level);
        
        num = getSubsystem().getSettingsValue("panel:gadgets:motor2").getInteger();
        motor2_ = mapButton(num, OIPanelButton.ButtonType.Level);

    }  
}