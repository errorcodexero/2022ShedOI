package frc.robot;

import org.xero1425.base.XeroRobot;
import org.xero1425.base.subsystems.RobotSubsystem;
import org.xero1425.base.subsystems.motorsubsystem.MotorSubsystem;


public class ShedOIRobotSubsystem extends RobotSubsystem {

    private MotorSubsystem motor1Subsystem;
    private MotorSubsystem motor2Subsystem;
    public ShedOIRobotSubsystem(XeroRobot robot) throws Exception {
        super(robot, "ShedOIRobotSubsystem") ;

        motor1Subsystem = new MotorSubsystem(this, "motor1Subsystem");
        motor2Subsystem = new MotorSubsystem(this, "motor2Subsystem");

    }

    public MotorSubsystem getMotor1Subsystem() {
        return motor1Subsystem;
    }
    public MotorSubsystem getMotor2Subsystem() {
        return motor2Subsystem;
    }
}
