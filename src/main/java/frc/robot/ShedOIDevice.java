package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import org.xero1425.base.actions.Action;
import org.xero1425.base.subsystems.motorsubsystem.MotorPowerAction;
import org.xero1425.base.subsystems.motorsubsystem.MotorSubsystem;
import org.xero1425.base.subsystems.oi.OIPanel;
import org.xero1425.base.subsystems.oi.OIPanelButton;
import org.xero1425.base.subsystems.oi.OISubsystem;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.ISettingsSupplier;
import org.xero1425.misc.MissingParameterException;
import org.xero1425.base.actions.InvalidActionRequest;

public class ShedOIDevice extends OIPanel {
    private MotorPowerAction motor1PowerAction;
    private MotorPowerAction motor2PowerAction;
    private int motor1_enable_;
    private int motor2_enable_;
    private int motor1_reverse_;
    private int motor2_reverse_;
    private int motor1_adjust_num_; // read this analog value -1 to 1 directly
    private int motor2_adjust_num_; // read this analog value -1 to 1 directly

    private double power1 ;
    private double power2 ;

    private Action motor1OffAction;
    private Action motor2OffAction;
    private MotorSubsystem motor1Subsystem;
    private MotorSubsystem motor2Subsystem;


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
    public void generateActions() {
        int sign1 = 1 ;
        int sign2 = 1 ;

        //TODO: update motor1PowerAction with analog power (-1 to 1) scaled to (0 to 1) power

        //motor 1 actions
        if (getValue(motor1_enable_) == 0) {
            motor1Subsystem.setAction(motor1OffAction) ;         
        }
        if (getValue(motor1_enable_) == 1) {
            if (getValue(motor1_reverse_) == 1) {//reverse
                sign1 = -1 ;
            }
            motor1PowerAction.update(power1*sign1) ;
            if (motor1Subsystem.getAction() != motor1PowerAction) {
                motor1Subsystem.setAction(motor1PowerAction) ;   
            }
        }

        //motor 2 actions
        if (getValue(motor2_enable_) == 0) {
            motor1Subsystem.setAction(motor1OffAction) ; 
        }
        if (getValue(motor2_enable_) == 1) {
            if (getValue(motor2_reverse_) == 1) {//reverse
                sign2 = -1 ;
            }
            motor2PowerAction.update(power2*sign2) ;
            if (motor2Subsystem.getAction() != motor2PowerAction) {
                motor2Subsystem.setAction(motor2PowerAction) ;   
            }
        }

    }

    @Override
    public void computeState() throws Exception {
        super.computeState() ;

        //get from the (joystick_val + 1)/2 
        double v = DriverStation.getStickAxis(getIndex(), motor1_adjust_num_) ;
        power1 = (v+1)/2; 

        v = DriverStation.getStickAxis(getIndex(), motor2_adjust_num_) ;
        power2 = (v+1)/2;
    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num ;
        
        //TODO: add motor1 and motor2 to jsonc file
        num = getSubsystem().getSettingsValue("panel:gadgets:motor1_enable").getInteger();
        motor1_enable_ = mapButton(num, OIPanelButton.ButtonType.Level);
        
        num = getSubsystem().getSettingsValue("panel:gadgets:motor2_enable").getInteger();
        motor2_enable_ = mapButton(num, OIPanelButton.ButtonType.Level);

        num = getSubsystem().getSettingsValue("panel:gadgets:motor1_reverse").getInteger();
        motor1_reverse_ = mapButton(num, OIPanelButton.ButtonType.Level);
        
        num = getSubsystem().getSettingsValue("panel:gadgets:motor2_reverse").getInteger();
        motor2_reverse_ = mapButton(num, OIPanelButton.ButtonType.Level);
    
        
        motor1_adjust_num_ = getSubsystem().getSettingsValue("panel:gadgets:motor1_adjust").getInteger();
        // motor1_adjust_ = mapButton(num, OIPanelButton.ButtonType.Level);

        motor2_adjust_num_ = getSubsystem().getSettingsValue("panel:gadgets:motor2_adjust").getInteger();
        // motor2_adjust_ = mapButton(num, OIPanelButton.ButtonType.Level);

    }  
}