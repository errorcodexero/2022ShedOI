package frc.robot;

import org.xero1425.base.actions.Action;
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

        motor1PowerAction = new MotorPowerAction(motor1Subsystem, 0);
        motor2PowerAction = new MotorPowerAction(motor2Subsystem, 0);

    }

    @Override
    public void generateActions() throws InvalidActionRequest {
    }


    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num ;

    }
}