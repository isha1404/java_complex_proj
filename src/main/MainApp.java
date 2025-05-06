package main;

import launcher.ProcessLauncher;
import db.SybaseConnector;
import messaging.MessagingService;
import monitoring.RvdMonitor;
import user.UserService;
import config.VaultManager;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Starting ProcessMonitorMessenger...");

        System.out.println("Initializing secure vault...");
        
        ProcessLauncher.launchProcess();
        SybaseConnector.connect();
        MessagingService.start();
        RvdMonitor.startMonitoring();
        
        String adminUsername = "admin";
        String adminPassword = VaultManager.getSecret("admin.password");
        if (adminPassword == null) {
            adminPassword = "admin_" + System.currentTimeMillis();
            VaultManager.setSecret("admin.password", adminPassword);
        }
        UserService.register(adminUsername, adminPassword);
        
        System.out.println("ProcessMonitorMessenger started successfully");
    }
}
