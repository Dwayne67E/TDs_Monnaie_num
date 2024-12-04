# Ethereum Node Deployment Workshop

## Overview
Deploy a fully functional and synchronized Ethereum node on a school VPS, building the clients from source and ensuring proper configuration and accessibility.

## Prerequisites
- Access to school VPS
- 2TB storage available
- Basic understanding of Linux commands
- Git installed
- Required build tools (will be detailed in setup)

## Technical Requirements

### Node Specifications
- **Type**: Full node (not light or archive)
- **Storage**: Must fit within 2TB limit
- **Clients**: Both execution and consensus layer clients
- **Build**: Must be compiled from source (no Docker or pre-built binaries)
- **Access**: Public RPC endpoints must be exposed
- **Persistence**: Service must auto-start on system boot

### Network Access
- HTTP and WebSocket endpoints must be accessible
- Both eth and web3 namespaces must be enabled
- Proper security measures must be implemented

## Tasks Breakdown

### 1. Environment Setup 
1. Update system and install required build dependencies
2. Configure system resources and storage
3. Document system specifications and preparations

### 2. Client Selection and Build 
1. Choose appropriate execution client (e.g., Geth, Nethermind, or Besu)
   - Document reasoning for client selection
   - Consider storage requirements
   - [Here is a list of the available clients](https://ethereum.org/en/developers/docs/nodes-and-clients/)
2. Choose consensus client (e.g., Lighthouse, Prysm, or Teku)
   - Document reasoning for client selection
   - Consider resource requirements
   - [Here is a list of the available clients](https://ethereum.org/en/developers/docs/nodes-and-clients/)
3. Build both clients from source
   - Document all build steps
   - Include any encountered issues and solutions

### 3. Configuration and Synchronization 
1. Configure both clients
   - Network selection (mainnet)
   - RPC settings
   - Security parameters
2. Start initial synchronization
   - Monitor progress
   - Document sync times
   - Track resource usage

### 4. RPC Endpoint Setup
1. Configure HTTP and WebSocket endpoints
2. Enable required namespaces (eth and web3)
3. Implement security measures
4. Test endpoint accessibility and functionality

### 5. Service Configuration
1. Create systemd service files
2. Configure auto-start on boot
3. Test system reboot and automatic recovery

## Deliverables

### GitHub Repository Content
1. README.md with:
   - Project overview
   - System requirements
   - Step-by-step installation guide
2. Configuration files (with sensitive data removed)
3. Service files
4. Screenshots showing:
   - Build process completion
   - Sync progress
   - RPC endpoint testing
   - Service status after reboot

### Documentation Requirements
- Clear step-by-step instructions
- Command explanations
- Screenshots for crucial steps
- Troubleshooting section
- Resource monitoring data

### Submission
1. Create GitHub repository
2. Complete all documentation
3. Add repository URL to the provided spreadsheet:
   https://docs.google.com/spreadsheets/d/1UGBgVgMxLx-VxrtgCIDSuw3SQ-rjui-Y4uLgNXOls2Y/edit?gid=0

## Evaluation Criteria

### Technical Implementation
- Successful client compilation
- Full node synchronization
- RPC endpoint functionality
- Service auto-start reliability
- Security considerations

### Documentation Quality
- Clarity and completeness
- Screenshot quality and relevance
- Command explanations
- Troubleshooting guidance
- Overall presentation

## Tips for Success
- Start with client selection research
- Monitor system resources during sync
- Document issues and solutions
- Test endpoints thoroughly
- Verify auto-start functionality multiple times
- Keep security in mind when exposing RPC endpoints

## Common Pitfalls to Avoid
- Insufficient storage planning
- Missing build dependencies
- Insecure RPC configurations
- Incomplete service files
- Poor documentation of issues encountered


