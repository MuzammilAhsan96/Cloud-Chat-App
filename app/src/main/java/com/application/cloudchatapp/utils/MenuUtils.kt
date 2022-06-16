package com.application.cloudchatapp.utils

object MenuUtils {

   /* fun setHomeTabListener(
        activity: BaseActivity,
        includeHomeTab: IncludeBottomTabHomeBinding,
        viewPagerHome: NonSwipeAbleViewPager
    ) {
        includeHomeTab.apply {
            linearTabCon.setOnClickListener {
                ivTabCon.setColorFilter(AppUtil.getColor(R.color.black))
                tvTabCon.setTextColor(AppUtil.getColor(R.color.color_2F2F2F))
                viewTabCon.visible()

                ivTabTicket.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabTicket.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabTicket.invisible()

                ivTabBrand.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabBrand.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabBrand.invisible()

                ivTabMyEvent.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabMyEvent.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabMyEvent.invisible()

//                viewPagerHome.currentItem = 0
                viewPagerHome.setCurrentItem(0, false)
                activity.onTabClick(0)
            }

            linearTabTicket.setOnClickListener {

                ivTabCon.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabCon.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabCon.invisible()

                ivTabTicket.setColorFilter(AppUtil.getColor(R.color.black))
                tvTabTicket.setTextColor(AppUtil.getColor(R.color.color_2F2F2F))
                viewTabTicket.visible()

                ivTabBrand.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabBrand.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabBrand.invisible()

                ivTabMyEvent.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabMyEvent.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabMyEvent.invisible()

//                viewPagerHome.currentItem = 1
                viewPagerHome.setCurrentItem(1, false)
                activity.onTabClick(1)
            }

            linearTabScan.setOnClickListener {
                //showToast("Scan In Progress")
                activity.launchActivity(ScannerActivity::class.java)

                activity.onTabClick(2)
            }

            linearTabBrand.setOnClickListener {

                ivTabCon.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabCon.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabCon.invisible()

                ivTabTicket.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabTicket.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabTicket.invisible()

                ivTabBrand.setColorFilter(AppUtil.getColor(R.color.black))
                tvTabBrand.setTextColor(AppUtil.getColor(R.color.color_2F2F2F))
                viewTabBrand.visible()

                ivTabMyEvent.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabMyEvent.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabMyEvent.invisible()

//                viewPagerHome.currentItem = 2
                viewPagerHome.setCurrentItem(2, false)

                activity.onTabClick(2)
            }

            linearTabMyEvent.setOnClickListener {
                *//*ivTabCon.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabCon.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabCon.invisible()

                ivTabTicket.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabTicket.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabTicket.invisible()

                ivTabBrand.setColorFilter(AppUtil.getColor(R.color.color_8C969B))
                tvTabBrand.setTextColor(AppUtil.getColor(R.color.color_8C969B))
                viewTabBrand.invisible()

                ivTabMyEvent.setColorFilter(AppUtil.getColor(R.color.black))
                tvTabMyEvent.setTextColor(AppUtil.getColor(R.color.color_2F2F2F))
                viewTabMyEvent.visible()*//*

//                viewPagerHome.currentItem = 3
                activity.launchActivity(EventActivity::class.java)

                activity.onTabClick(3)
            }
        }

    }

    fun setMenuDrawer(activity: BaseActivity, includeDrawer: LayoutDrawerBinding) {
        val isLogin = PreferenceKeeper.getInstance().isLogin
        includeDrawer.apply {
            if (isLogin) {
                linearProfileWithoutLogin.gone()
                linearProfileWithLogin.visible()

                linearMenuAboutUs.gone()
                linearMenuMyProfile.visible()

                linearMenuConnection.visible()
                AppUtil.setBgColor(linearMenuConnection, AppUtil.getColor(R.color.color_f7f7f7))

                linearMenuProduct.visible()

                linearMenuChangePass.visible()

                linearMenuHelp.visible()
                AppUtil.setBgColor(linearMenuHelp, AppUtil.getColor(R.color.color_f7f7f7))

                linearMenuPrvPlc.visible()

                btnLogout.visible()

                val userProfile = AppUtil.getUserProfile()

                GlideUtils.loadImageProfile(ivMenuProfile, userProfile.image)
                tvMenuName.text = AppUtil.getFullName(userProfile.firstName, userProfile.lastName)



                *//*val isQuestionnaire = PreferenceKeeper.getInstance().isQuestionnaire
                if (isQuestionnaire) {
                    linearSwitch.visible()
                } else {
                    linearSwitch.gone()
                }*//*

            } else {
                linearProfileWithoutLogin.visible()
                linearProfileWithLogin.gone()

                linearMenuAboutUs.visible()
                linearMenuMyProfile.gone()
                linearMenuConnection.gone()
                linearMenuProduct.gone()
                linearMenuChangePass.gone()
                linearMenuHelp.visible()
                AppUtil.setBgColor(linearMenuHelp, AppUtil.getColor(R.color.color_f7f7f7))
                linearMenuPrvPlc.visible()
                btnLogout.gone()
                linearSwitch.gone()
                btnMenuSignIn.setOnClickListener { view ->
                    activity.launchActivity(JoinActivity::class.java, view)
                }
            }
        }
    }*/
}