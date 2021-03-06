package code.name.monkey.retromusic.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.name.monkey.appthemehelper.ThemeStore;
import code.name.monkey.appthemehelper.util.ToolbarContentTintHelper;
import code.name.monkey.retromusic.R;
import code.name.monkey.retromusic.helper.MusicPlayerRemote;
import code.name.monkey.retromusic.ui.activities.base.AbsMusicServiceActivity;
import code.name.monkey.retromusic.ui.fragments.PlayingQueueFragment;
import code.name.monkey.retromusic.util.MusicUtil;


public class PlayingQueueActivity extends AbsMusicServiceActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindDrawable(R.drawable.ic_keyboard_backspace_black_24dp)
    Drawable close;

    @BindView(R.id.player_queue_sub_header)
    TextView textView;

    @BindString(R.string.queue)
    String queue;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_queue);
        ButterKnife.bind(this);

        setStatusbarColorAuto();
        setNavigationbarColorAuto();
        setTaskDescriptionColorAuto();
        setLightNavigationBar(true);

        setupToolbar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PlayingQueueFragment())
                    .commit();
        }
    }

    protected String getUpNextAndQueueTime() {
        return getResources().getString(R.string.up_next) + "  •  " + MusicUtil.getReadableDurationString(MusicPlayerRemote.getQueueDurationMillis(MusicPlayerRemote.getPosition()));
    }

    private void setupToolbar() {
        title.setTextColor(ThemeStore.textColorPrimary(this));
        textView.setText(getUpNextAndQueueTime());
        textView.setTextColor(ThemeStore.accentColor(this));

        appBarLayout.setBackgroundColor(ThemeStore.primaryColor(this));
        toolbar.setBackgroundColor(ThemeStore.primaryColor(this));
        toolbar.setNavigationIcon(close);
        setSupportActionBar(toolbar);
        setTitle(null);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        ToolbarContentTintHelper.colorBackButton(toolbar, ThemeStore.accentColor(this));
    }
}
