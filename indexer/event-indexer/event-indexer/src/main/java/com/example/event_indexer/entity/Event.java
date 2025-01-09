package com.example.event_indexer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Log;

@Entity
public class Event {

    @Id
    private String userOpHash;
    
    private String sender;
    private String paymaster;
    private BigInteger nonce;
    private boolean success;
    private BigInteger actualGasCost;
    private BigInteger actualGasUsed;
    private BigInteger blockNumber;
    
    public String getUserOpHash() {
        return userOpHash;
    }

    public void setUserOpHash(String userOpHash) {
        this.userOpHash = userOpHash;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPaymaster() {
        return paymaster;
    }

    public void setPaymaster(String paymaster) {
        this.paymaster = paymaster;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BigInteger getActualGasCost() {
        return actualGasCost;
    }


    public void setActualGasCost(BigInteger actualGasCost) {
		this.actualGasCost = actualGasCost;
	}
	public BigInteger getActualGasUsed() {
        return actualGasUsed;
    }

    public BigInteger getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(BigInteger blockNumber) {
		this.blockNumber = blockNumber;
	}

	public void setActualGasUsed(BigInteger actualGasUsed) {
        this.actualGasUsed = actualGasUsed;
    }
}

