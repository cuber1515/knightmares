package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.methodForEncoders;


@Autonomous
public class autonomousCodeForRedRight extends LinearOpMode {

    // start encoders
    public void startEncoders() {
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // exit encoders
    public void exitEncoders() {
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // restart encoders
    public void resetEncoders() {
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // move forward or backward
    public void goForward(int tIme, int power) {
        FR.setPower(power);
        FL.setPower(power);
        BR.setPower(power);
        BL.setPower(power);

        if (tIme != 0) { // IF THIS IS SET TO ANY NUMBER OTHER THAN 0 IT WILL MAKE IT SO THAT IT GOES
            sleep(tIme); // FORWARD FOR HOWEVER LONG IT IS SET TO, IF IT'S 0 IT WILL JUST SET
            // THE MOTORS TO THE POWER INDEFINITELY
            FR.setPower(0);
            FL.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
        }
    }

    public void goBackward(int tIme, double power) {
        FR.setPower(-power);
        FL.setPower(-power);
        BR.setPower(-power);
        BL.setPower(-power);

        if (tIme != 0) { // IF THIS IS SET TO ANY NUMBER OTHER THAN 0 IT WILL MAKE IT SO THAT IT GOES
            sleep(tIme); // BACKWARD FOR HOWEVER LONG IT IS SET TO, IF IT'S 0 IT WILL JUST SET
            // THE MOTORS TO THE POWER INDEFINITELY
            FR.setPower(0);
            FL.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
        }
    }

    // turn
    public void turn(int tIme, String direction) {
        if (direction == "right") {
            FL.setPower(0.5);
            BL.setPower(0.5);
            FR.setPower(-0.5);
            BR.setPower(-0.5);
        } else if (direction == "left") {
            FR.setPower(0.5);
            BR.setPower(0.5);
            FL.setPower(-0.5);
            BL.setPower(-0.5);
        }

        if (tIme != 0) { // IF THIS IS SET TO ANY NUMBER OTHER THAN 0 IT WILL MAKE IT SO THAT IT
            sleep(tIme); // TURNS FOR HOWEVER LONG IT IS SET TO, IF IT'S 0 IT WILL JUST SET
            // THE MOTORS TO THE POWER INDEFINITELY
            FR.setPower(0);
            FL.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
        }
    }

    // some encoders
    public void encoders(int targetToPlace) {

        startEncoders();

        FL.setTargetPosition(targetToPlace);
        FR.setTargetPosition(targetToPlace);
        BL.setTargetPosition(targetToPlace);
        BR.setTargetPosition(targetToPlace);

        FL.setPower(0.25);
        FR.setPower(0.25);
        BL.setPower(0.25);
        BR.setPower(0.25);

        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FR.isBusy() || FL.isBusy() || BR.isBusy() || BL.isBusy()) {
        }

        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
    }

    DcMotor FR, FL, BR, BL, DCM, AM;
    Servo CS;

    methodForEncoders encoders = new methodForEncoders();

    @Override
    public void runOpMode() throws InterruptedException {

        // All motors
        FL = hardwareMap.dcMotor.get("Front Right");
        FR = hardwareMap.dcMotor.get("Front Left");
        BL = hardwareMap.dcMotor.get("Back Right");
        BR = hardwareMap.dcMotor.get("Back Left");
        DCM = hardwareMap.dcMotor.get("Duck");
        CS = hardwareMap.servo.get("Claw");
        AM = hardwareMap.dcMotor.get("Arm Motor");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        int firstTurn = (int)  (537.689 / 1.8);
        int secondTurn = (int) (537.689 / 2.5);

        // THIS CLOSES THE CLAW
        CS.setPosition(1);

        resetEncoders();

        waitForStart();

        // GO FORWARD 16 IN
        int targetToPlace = (int) ((537.689/(3.77953*3.1415926535))*16);
        encoders(targetToPlace);

        exitEncoders();

        // LIFT ARM UP
        AM.setPower(-0.4);
        sleep(2000);
        AM.setPower(0);

        resetEncoders();
        sleep(500);
        startEncoders();

        // TURN TO THE RIGHT A BIT SO I CAN DROP OFF THE FREIGHT
        FL.setTargetPosition(-firstTurn);
        FR.setTargetPosition(firstTurn);
        BL.setTargetPosition(-firstTurn);
        BR.setTargetPosition(firstTurn);

        FL.setPower(0.25);
        FR.setPower(0.25);
        BL.setPower(0.25);
        BR.setPower(0.25);

        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FR.isBusy() || FL.isBusy() || BR.isBusy() || BL.isBusy()) {
        }

        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);

        // DROP FREIGHT
        CS.setPosition(0.02);
        sleep(500);
        CS.setPosition(1);
        sleep(800);
        CS.setPosition(0.5);

        resetEncoders();

        // TURN A BIT MORE SO IT CAN REVERSE INTO THE WAREHOUSE
        FL.setTargetPosition(-secondTurn);
        FR.setTargetPosition(secondTurn);
        BL.setTargetPosition(-secondTurn);
        BR.setTargetPosition(secondTurn);

        FL.setPower(0.2);
        FR.setPower(0.2);
        BL.setPower(0.2);
        BR.setPower(0.2);

        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FR.isBusy() || FL.isBusy() || BR.isBusy() || BL.isBusy()) {
        }

        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);

        exitEncoders();

        // REVERSE INTO WAREHOUSE
        goBackward(900, 0.75);

        // LOWER ARM
        AM.setPower(0.4);
        sleep(1000);
        AM.setPower(0);
    }
}
