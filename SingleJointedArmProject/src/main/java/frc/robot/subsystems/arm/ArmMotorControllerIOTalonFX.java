package frc.robot.subsystems.arm;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ArmMotorControllerIOTalonFX implements ArmMotorControllerIO {
    private final TalonFX arm = new TalonFX(0);

    private final StatusSignal<Double> armPosition = arm.getPosition();
    private final StatusSignal<Double> armVelocity = arm.getVelocity();
    private final StatusSignal<Double> appliedVolts = arm.getMotorVoltage();
    private final StatusSignal<Double> currentAmps = arm.getStatorCurrent();

    public ArmMotorControllerIOTalonFX() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.CurrentLimits.StatorCurrentLimit = 30;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        arm.getConfigurator().apply(config);

        BaseStatusSignal.setUpdateFrequencyForAll(
        50.0, armPosition, armVelocity, appliedVolts, currentAmps);
        arm.optimizeBusUtilization();   
    }

    @Override
    public void updateInputs(ArmMotorControllerIOInputs inputs) {
        BaseStatusSignal.refreshAll(armPosition, armVelocity, appliedVolts, currentAmps);
        inputs.armPosition = armPosition.getValueAsDouble();
        inputs.armVelocity = armVelocity.getValueAsDouble();
        inputs.appliedVolts = appliedVolts.getValueAsDouble();
        inputs.currentAmps = currentAmps.getValueAsDouble();
    }

    @Override
    public void setPosition(double position) {
        arm.setControl(new PositionVoltage(position));
    }

    @Override
    public void setVelocity(double velocity) {
        arm.setControl(new VelocityVoltage(velocity));
    }

    @Override
    public void stop() {
       arm.stopMotor();
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        Slot0Configs configs = new Slot0Configs();

        configs.kP = kP;
        configs.kI = kI;
        configs.kD = kD;

        arm.getConfigurator().apply(configs);
    }
}
