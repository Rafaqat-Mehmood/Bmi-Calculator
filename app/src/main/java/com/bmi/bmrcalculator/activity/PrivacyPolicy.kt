package com.bmi.bmrcalculator.activity

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bmi.bmrcalculator.R
import com.bmi.bmrcalculator.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicy : AppCompatActivity() {
    private lateinit var  binding: ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            backIcon.setOnClickListener {
                onBackPressed()
            }

            webViewPrivacy.webViewClient = WebViewClient()
            webViewPrivacy.settings.javaScriptEnabled = false

            val htmlContent = """
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <style>
                    body { font-family: sans-serif; padding: 20px; line-height: 1.6; color: #333; }
                    h2, h3 { color: #2E7D32; }
                    a { color: #1976D2; text-decoration: none; }
                    ul { margin-left: 16px; }
                </style>
            </head>
            <body>

            <h2>INFORMATION COLLECTION RELATING TO CHILDREN</h2>
            <p>
            We consider ourselves to be family oriented, however we have decided to voluntarily implement higher child protection standards even though our products and services are not directed to, and we do not knowingly collect personal information from, children under 13. If we learn that we have collected personal information of a child under 13, we will take all reasonable steps to delete the information as soon as possible. If you become aware that your child has provided us with personal information without your consent, please contact us at 
            <a href="mailto:minddev39@gmail.com">minddev39@gmail.com</a>.
            </p>

            <p>
            In order to preserve the family-friendly reputation of our Applications, we age-gated some of the features in our applications, so that they are not available for use by children under 13. The age gate is being conducted after the download of some of our apps for specific territories in order to assure the privacy and protection of younger users of our apps. If you do not pass the gate the features enabling potential collection and sharing of personally identifiable information are disabled for your device ID. For your safety, it is not possible to change your age later on. We do not store your year of birth but only the information whether your device ID passed the age gate or not.
            </p>

            <p>
            The only personal information we collect from you for the purposes of offering our services are persistent identifiers (such as device ID, IDFA, IDFV, IP address).
            </p>

            <p>
            When using the persistent identifiers we will not contact you, display behavioral advertising to you, nor do any user tracking or profiling. In addition, we strive that all of our advertising partners adhere to the same standards for the collection, usage and disclosure of your personal information as we do, but we can however not be responsible if they breach the commitments they gave to us regarding the collection of information. In case of such breaches you agree that our liability is limited to the amounts we are able to receive as indemnification for the breaches from our advertising partner. For the detailed information about disclosure to third parties please see section "Disclosure To Third Parties" of this Privacy Policy. For any additional questions about third party information disclosure please refer to 
            <a href="mailto:minddev39@gmail.com">minddev39@gmail.com</a>.
            </p>

            <p>
            We will send push notifications to users who allow us to do so in order to reengage them in terms of using the Application. You may unsubscribe at any time by following the instructions in section Push Notifications of this Privacy Policy. We strive to ensure that the content of the push notifications will always be family-friendly. Push notifications are local, contextual and sent in app only and not from a server or in conjunction with other user information.
            </p>

            <h3>Developers Policy</h3>
            <p>
            Developers of all apps respect user data and conscientiously implement privacy policy statements. This Privacy Policy informs you of your choices and our practices regarding any information you provide to us and we collect. This privacy statement applies to Super Fast Apps domain on Google Play Store and its products. When you use these services—and any others we release—you’ll inevitably share some information with us. Since your privacy means much to us, we want you to learn about the information we collect, how we use it, whom we share it with, and the choices we give you to control, access, and update your information.
            </p>

            <p>
            If someone decides to use my service, this page is used to inform visitors of my personal information collection, use and disclosure policies.
            </p>

            <p>
            If you choose to use my service, you agree to the collection and use of information related to this strategy. Personal information I collect is used to provide and improve services. I will not use or share your information with anyone other than as stated in this privacy policy.
            </p>

            <h3>How We Use Information</h3>
            <ul>
                <li>Provide and deliver the products, services, and functionality that you request and are necessary for our apps to work.</li>
                <li>Develop, improve, and refine products, services, and functionality.</li>
                <li>Personalize the Services by providing advertisements, content, or features that match user profiles or interests.</li>
            </ul>

            <h3>Personal Information</h3>
            <p>
            We do not record, store, or collect information that identifies you as an individual (a person). We do not attempt to obtain any sensitive information like contacts, your device’s media, or documents. Super Fast Apps will not sell, trade, or disclose any personal information you might give us without your prior consent. However, information will be disclosed where required by law.
            </p>

            <p>
            Although we ourselves do not attempt to track your information, our products may contain third parties’ APIs. This Privacy Policy does not apply to information that may be collected by that third party, so we are therefore not responsible for that information.
            </p>

            <h3>Our Third Parties' Privacy Policies Are As Follows:</h3>
            <p>(Insert your third-party partner privacy links here)</p>

            <h3>Contact Information</h3>
            <p>
            If you have any questions or comments about this Policy or our privacy practices, or to report any violations of the Policy or abuse of an Application, please contact us at 
            <a href="mailto:minddev39@gmail.com">minddev39@gmail.com</a>.
            </p>

            <h3>Children’s Privacy</h3>
            <p>
            These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to take necessary actions.
            </p>

            <h3>Policy Updates</h3>
            <p>This policy may be updated from time to time. Any changes will be posted within the app.</p>

            </body>
            </html>
        """.trimIndent()
            webViewPrivacy.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
        }
    }
}