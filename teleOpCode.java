package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class teleOpCode extends LinearOpMode {

    // some encoders
    public void encoders(int rotations) {
        AM.setTargetPosition(rotations);
        AM.setPower(0.4);
        AM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (AM.isBusy()) {
        }
        AM.setPower(0);
    }

    DcMotor FR, FL, BR, BL, DCM, AM;
    Servo CS;

    methodForEncoders encoders = new methodForEncoders();

    @Override
    public void runOpMode() throws InterruptedException {

        FR = hardwareMap.dcMotor.get("Front Right");
        FL = hardwareMap.dcMotor.get("Front Left");
        BR = hardwareMap.dcMotor.get("Back Right");
        BL = hardwareMap.dcMotor.get("Back Left");
        DCM = hardwareMap.dcMotor.get("Duck");
        CS = hardwareMap.servo.get("Claw");
        AM = hardwareMap.dcMotor.get("Arm Motor");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {

            while (opModeIsActive()) {

                // MOVE ROBOT
                FL.setPower(gamepad1.right_stick_y * 0.5);
                BL.setPower(gamepad1.right_stick_y * 0.5);
                FR.setPower(gamepad1.left_stick_y * 0.5);
                BR.setPower(gamepad1.left_stick_y * 0.5);

                // TURN ON DUCK MOTOR
                if (gamepad1.left_bumper) {
                    DCM.setPower(-0.5);
                }

                // TURN ON DUCK MOTOR
                if (gamepad1.right_bumper) {
                    DCM.setPower(0.5);
                }

                // TURN OFF DUCK MOTOR
                if (gamepad1.a) {
                    DCM.setPower(0);
                }

                // LIFT ARM
                if (gamepad1.right_trigger != 0) {
                    AM.setPower(0.4);
                    sleep(300);
                    AM.setPower(0);
                }

                // LOWER ARM
                if (gamepad1.left_trigger != 0) {
                    AM.setPower(-0.4);
                    sleep(300);
                    AM.setPower(0);
                }

                // OPEN CLAW
                if (gamepad1.x) {
                    CS.setPosition(0.02);
                }

                // CLOSE CLAW
                if (gamepad1.b) {
                    CS.setPosition(1);
                }

                // TURN CLAW OFF
                if (gamepad1.dpad_up) {
                    CS.setPosition(0.5);
                }
            }
        }
    }
}
