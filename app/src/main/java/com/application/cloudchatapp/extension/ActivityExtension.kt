package com.application.cloudchatapp.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    startActivity(
        Intent(this, activity)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    )
}

fun <A : Activity> Activity.authIntent(email: String, activity: Class<A>) {
    startActivity(
        Intent(this, activity)
            .putExtra("_id", intent.getStringExtra("_id"))
            .putExtra("email", email)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    )
}

/*fun Activity.errorDialog(message: String?) {
    val dialog = ErrorDialog()
    val bundle = Bundle()
    bundle.putString("message", message ?: "")
    dialog.arguments = bundle
    dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_NoTitle);
    dialog.show((this as AppCompatActivity).supportFragmentManager, "")
}

fun Activity.showTitle(isHide: Boolean) {
    val toolbar = findViewById<Toolbar>(R.id.clMainToolbar)
    val tvTitle = toolbar.findViewById<TextView>(R.id.tvTitle)
    tvTitle.visible(isHide)

}*/

fun Activity.openLinkExternally(link: String?) {
    var url: String = link ?: ""
    if (!url.startsWith("http://") && !url.startsWith("https://"))
        url = "http://$url"

    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

/*
fun Activity.mainToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.clMainToolbar)
    val navController = findNavController(R.id.fragment)
    val navigationView = findViewById<NavigationView>(R.id.navigationView)

    val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

    // Guest User
    val tvTitle = toolbar.findViewById<TextView>(R.id.tvTitle)

    // Register User
    val ivLogo = toolbar.findViewById<ImageView>(R.id.ivLogo)
    val pbEventLoader = toolbar.findViewById<ProgressBar>(R.id.pbEventLoader)
    val cvEventLogo = toolbar.findViewById<MaterialCardView>(R.id.cvEventLogo)
    val ivHeart = toolbar.findViewById<ImageView>(R.id.ivHeart)
    val ivNotification = toolbar.findViewById<ImageView>(R.id.ivNotification)
    val clSubDrawer = findViewById<ConstraintLayout>(R.id.clSubDrawer)

    if (PreferenceKeeper.getInstance().isLogin == true && intent?.getBooleanExtra(
            "isSwitch",
            true
        ) == true
    ) {
        cvEventLogo.visible(true)
        ivHeart.visible(true)
        ivNotification.visible(true)
        tvTitle.visible(false)
        clSubDrawer.visible(true)
        drawerLayout.setBackgroundResource(R.drawable.drawable_auth)
        navController.graph = navController.navInflater.inflate(R.navigation.main_nav)
        val bundle = Bundle()
        bundle.putString("id", intent?.getStringExtra("id"))
        findNavController(R.id.fragment).navigate(R.id.conferenceFragment, bundle)
        navigationView.setItemTextAppearance(R.style.avantgarde_medium_menu_user)
        navigationView.inflateHeaderView(R.layout.layout_register_navigation_header)
        navigationView.inflateMenu(R.menu.menu_register_drawer)


    } else {
        cvEventLogo.visible(false)
        ivHeart.visible(false)
        ivNotification.visible(false)
        tvTitle.visible(true)


        drawerLayout.setBackgroundResource(R.drawable.light_grey)
        navController.graph = navController.navInflater.inflate(R.navigation.guest_event_nav)

        if (intent?.getBooleanExtra("isSwitch", true) == false) {
            clSubDrawer.visible(true)
            navigationView.setItemTextAppearance(R.style.avantgarde_medium_menu_user)
            navigationView.inflateHeaderView(R.layout.layout_register_navigation_header)
            navigationView.inflateMenu(R.menu.menu_register_drawer)


        } else {
            clSubDrawer.visible(false)

            navigationView.setItemTextAppearance(R.style.avantgarde_medium_menu)
            navigationView.inflateHeaderView(R.layout.layout_guest_navigation_header)
            navigationView.inflateMenu(R.menu.menu_guest_drawer)
        }

    }
}
 */


/*fun Activity.noDataFound(boolean: Boolean) {
    findViewById<Toolbar>(R.id.clNoData).visible(boolean)
}*/










