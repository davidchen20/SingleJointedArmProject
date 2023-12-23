// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetArmPosition extends SequentialCommandGroup {
  /** Creates a new SetArmPosition. */
  public SetArmPosition(double position) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(() -> Robot.m_robotContainer.arm.setPosition(position), Robot.m_robotContainer.arm),
      new WaitUntilCommand(() -> Robot.m_robotContainer.arm.targetReached()),
      new InstantCommand(() -> Robot.m_robotContainer.arm.stopArm(), Robot.m_robotContainer.arm));
  }
}
