// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  private final ArmMotorControllerIO arm;
  private final ArmMotorControllerIOInputsAutoLogged aInputs = new ArmMotorControllerIOInputsAutoLogged();

  private final DoubleSolenoidIO ds;
  private final DoubleSolenoidIOInputsAutoLogged dsInputs = new DoubleSolenoidIOInputsAutoLogged();

  private final CompressorIO compressor;
  private final CompressorIOInputsAutoLogged cInputs = new CompressorIOInputsAutoLogged();

  private boolean toggle = false;
  private double target = 0;

  public Arm(ArmMotorControllerIO arm, DoubleSolenoidIO ds, CompressorIO compressor) {
    this.arm = arm;
    this.ds = ds;
    this.compressor = compressor;
  }

  @Override
  public void periodic() {
    arm.updateInputs(aInputs);
    ds.updateInputs(dsInputs);
    compressor.updateInputs(cInputs);

    Logger.processInputs("Arm", aInputs);
    Logger.processInputs("Brake", dsInputs);
    Logger.processInputs("Compressor", cInputs);

    Logger.recordOutput("Arm Target Reached", targetReached());
    // This method will be called once per scheduler run
  }

  public void toggleCompressor() {
    if (toggle) compressor.disableCompressor();
    else compressor.enableCompressor();
  }

  public void setPosition(double position) {
    target = position;
    Logger.recordOutput("Arm Target", target);
    disableBrake();
    arm.setPosition(position);
  }

  public void stopArm() {
    enableBrake();
    arm.stop();
  }

  private void enableBrake() {
    ds.setValue(Value.kForward);
  }

  private void disableBrake() {
    ds.setValue(Value.kOff);
  }

  public boolean targetReached() {
    return Math.abs(target - aInputs.armPosition) <= 50;
  }
}
