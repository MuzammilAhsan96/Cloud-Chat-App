package com.application.cloudchatapp.utils

object StaticDataHelper {

   /* fun eventCategoryForExhibition(isSwitch: Boolean?): MutableList<EventCategoryModel?>? {
        val list: MutableList<EventCategoryModel?>? = ArrayList()
        list?.add(
            EventCategoryModel(
                local_id = 0,
                _id = "",
                sectorId = "",
                name = App.INSTANCE.getString(R.string.desc),
                check = true
            )
        )

        if (PreferenceKeeper.getInstance().isLogin == true && isSwitch == true) {
            //   list?.add(EventCategoryModel(local_id = 0, _id = "", sectorId = "", name = App.INSTANCE.getString(R.string.lounge)))
            list?.add(
                EventCategoryModel(
                    local_id = 0,
                    _id = "",
                    sectorId = "",
                    name = App.INSTANCE.getString(R.string.spotlight)
                )
            )
            list?.add(
                EventCategoryModel(
                    local_id = 0,
                    _id = "",
                    sectorId = "",
                    name = App.INSTANCE.getString(R.string.timetable)
                )
            )
        } else {
            list?.add(
                EventCategoryModel(
                    local_id = 0,
                    _id = "",
                    sectorId = "",
                    name = App.INSTANCE.getString(R.string.stage)
                )
            )
        }
        list?.add(
            EventCategoryModel(
                local_id = 0,
                _id = "",
                sectorId = "",
                name = App.INSTANCE.getString(R.string.speaker)
            )
        )
        return list
    }


    fun termsAndCondition(context: Context?): MutableList<TermsConditionModel?>? {
        val list: MutableList<TermsConditionModel?>? = ArrayList()


        val wordtoSpan: Spannable = SpannableString(App.INSTANCE?.getString(R.string.terms))

        // Privacy Policy Link
        wordtoSpan.setSpan(object : ClickableSpan() {
            override fun onClick(vi: View) {
                context?.startActivity(
                    Intent(context, WebviewActivity::class.java).putExtra(
                        "url",
                        "https://professionalbeauty.co.uk/site/termsandconditions"
                    )
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds); }
        }, 38, 83, 0)

        wordtoSpan.setSpan(
            ForegroundColorSpan(Color.parseColor("#31A5FF")),
            38,
            83,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Privacy Policy Link
        wordtoSpan.setSpan(object : ClickableSpan() {
            override fun onClick(vi: View) {
                context?.startActivity(
                    Intent(context, WebviewActivity::class.java).putExtra(
                        "url",
                            "https://professionalbeauty.co.uk/site/privacypolicyandcookies"
                    )
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds); }
        }, wordtoSpan.length-16, wordtoSpan.length, 0)

        wordtoSpan.setSpan(
            ForegroundColorSpan(Color.parseColor("#31A5FF")),
            wordtoSpan.length-16,
            wordtoSpan.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        list?.add(TermsConditionModel(wordtoSpan, true))
        return list
    }

    fun getBrandCategories(data: MutableList<BrandData?>?): MutableList<EventCategoryModel?>? {
        val list: MutableList<EventCategoryModel?> = ArrayList()
        if (data != null && data.size > 0) {
            list.clear()
            list.add(EventCategoryModel(name = App.INSTANCE.getString(R.string.all)))
//        list?.add(EventCategoryModel(name = App.INSTANCE.getString(R.string.featured_brand)))
//        list?.add(EventCategoryModel(name = App.INSTANCE.getString(R.string.most_visited_brand)))
        }
        if (list.size > 0) {
            list[0]?.check = true
        }

        return list
    }

    fun getBrandDetailCateogry(): MutableList<BrandDetailModel?>? {
        val list: MutableList<BrandDetailModel?> = ArrayList()
        list.add(BrandDetailModel(name = "About us"))
        list.add(BrandDetailModel(name = "Staff on the Stand"))
        list.add(BrandDetailModel(name = "Products"))
        list.add(BrandDetailModel(name = "Offers"))
        list.add(BrandDetailModel(name = "Images/Videos"))
        list.add(BrandDetailModel(name = "Brochures"))

        return list
    }

    fun getWishListCategory(): MutableList<EventCategoryModel?> {
        val list: MutableList<EventCategoryModel?> = ArrayList()
        if (list.size == 0) {
            list.add(EventCategoryModel(name = App.INSTANCE.getString(R.string.products), check = true))
            list.add(EventCategoryModel(name = App.INSTANCE.getString(R.string.offers), check = false))
            list.add(EventCategoryModel(name = App.INSTANCE.getString(R.string.brands), check = false))
        }

        return list
    }*/
}