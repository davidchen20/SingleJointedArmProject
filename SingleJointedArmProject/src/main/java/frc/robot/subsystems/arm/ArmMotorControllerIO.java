package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmMotorControllerIO {
    @AutoLog
    public static class ArmMotorControllerIOInputs {
        public double armPosition = 0;
        public double armVelocity = 0;
        public double appliedVolts = 0;
        public double currentAmps = 0;
    }

    public void updateInputs(ArmMotorControllerIOInputs inputs);

    public void setPosition(double position);

    public void setVelocity(double velocity);

    public void stop();

    public void configurePID(double kP, double kI, double kD);
}
