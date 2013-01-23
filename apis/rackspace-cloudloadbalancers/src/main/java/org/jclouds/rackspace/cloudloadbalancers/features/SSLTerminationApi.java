/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.rackspace.cloudloadbalancers.features;

import org.jclouds.rackspace.cloudloadbalancers.domain.SSLTermination;

/**
 * The SSL Termination feature allows a load balancer user to terminate SSL traffic at the load balancer layer versus 
 * at the web server layer. A user may choose to configure SSL Termination using a key and an SSL certificate or an 
 * (Intermediate) SSL certificate.
 * <p/> 
 * When SSL Termination is configured on a load balancer, a secure shadow server is created that listens only for 
 * secure traffic on a user-specified port. This shadow server is only visible to and manageable by the system. 
 * Existing or updated attributes on a load balancer with SSL Termination will also apply to its shadow server. 
 * For example, if Connection Logging is enabled on an SSL load balancer, it will also be enabled on the shadow server 
 * and Cloud Files logs will contain log files for both.
 * <p/>
 * Notes
 * <ol>
 * <li>SSL Termination may only be configured on load balancers with non-secure protocols. For example, SSL Termination 
 * can be applied to an HTTP load balancer, but not to an HTTPS load balancer.</li>
 * <li>SSL-terminated load balancers decrypt the traffic at the traffic manager and pass unencrypted traffic to the 
 * back-end node. Because of this, the customer's back-end nodes don't know what protocol the client requested. 
 * Therefore the X-Forwarded-Proto (XFP) header has been added for identifying the originating protocol of an HTTP 
 * request as "http" or "https" depending on what protocol the client requested.</li>
 * <li>Not every service will return certificates in the proper order. Please verify that your chain of certificates 
 * matches that of walking up the chain from the domain to the CA root.</li>
 * </ol>
 * 
 * Warning
 * <ol>
 * <li>If SSL is enabled on a load balancer that is configured with nodes that are NOT in the same datacenter, then 
 * decrypted traffic will be sent in clear text over the public internet to the external node(s) and will no longer 
 * be secure.</li>
 * </ol>
 *
 * @see SSLTerminationAsyncApi
 * @author Everett Toews
 */
public interface SSLTerminationApi {
   /**
    * Create or update SSL termination.
    * </p>
    * The following attributes may be updated without overwriting a load balancer's existing SSL certificate and key 
    * specifications:
    * <ul>
    * <li>enabled</li>
    * <li>secureTrafficOnly</li>
    * <li>securePort</li>
    * </ul>
    * These configurable attributes must be provided (individually or grouped) in a request without specifying any 
    * certificate/key combination if a user does not want the system to overwrite the existing SSL certificate/key 
    * configuration.
    * </p>
    * If a user wants to replace the existing SSL configuration, a new certificate, privatekey, and securePort 
    * combination must be provided instead of, or in addition to, the optional/editable attributes.
    */
   void createOrUpdate(SSLTermination healthMonitor);

   /**
    * Get SSL termination info.
    */
   SSLTermination get();
   
   /**
    * Remove SSL termination.
    * 
    * @return true on a successful removal, false if the SSL termination was not found
    */
   boolean remove();
}