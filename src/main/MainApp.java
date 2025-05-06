package main;

import launcher.ProcessLauncher;
import db.SybaseConnector;
import messaging.MessagingService;
import monitoring.RvdMonitor;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Starting ProcessMonitorMessenger...");

        ProcessLauncher.launchProcess();
        SybaseConnector.connect();
        MessagingService.start();
        RvdMonitor.startMonitoring();
    }
}