package frc.robot;

import org.xero1425.base.subsystems.DriveBaseSubsystem;
import org.xero1425.base.subsystems.Subsystem;
import org.xero1425.base.subsystems.oi.OIDevice;
import org.xero1425.base.subsystems.oi.OISubsystem;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class ShedOISubsystem extends OISubsystem {
    private ShedOIDevice oipanel_ ;

    public final static String SubsystemName = "shedoi";
    private final static String OIHIDIndexName = "panel:index";

    public ShedOISubsystem(Subsystem parent, DriveBaseSubsystem db) {
        super(parent, SubsystemName, GamePadType.Standard, db, true) ;

        MessageLogger logger = getRobot().getMessageLogger() ;

        try {
            oipanel_ = new ShedOIDevice(this, "OI", 2) ;
            addHIDDevice(oipanel_) ;
        }
        catch(Exception ex) {
            logger.startMessage(MessageType.Error) ;
            logger.add("OI HID device was not created - ") ;
            logger.add(ex.getMessage()).endMessage(); ;
        }
    }

    public void run() throws  Exception {
        super.run();
    }

    
}