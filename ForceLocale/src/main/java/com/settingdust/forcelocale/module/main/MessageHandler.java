package com.settingdust.forcelocale.module.main;

import com.google.common.collect.ImmutableList;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHandler {
    private MainProvider mainProvider;

    public MessageHandler(MainProvider mainProvider) {
        this.mainProvider = mainProvider;
    }

    @Listener
    public void onMessage(MessageChannelEvent event) {
        Set<MainEntity> entities = mainProvider.getEntities();
        for (MainEntity entity : entities) {
            Map<String, List<String>> locales = entity.getMessages();
            for (String regex : locales.keySet()) {
                Text message = event.getMessage();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(message.toPlain());
                if (matcher.find()) {
                    List<String> list = locales.get(regex);
                    Text.Builder text = Text.builder().format(message.getFormat());
                    for (Text child : getChildren(message)) {
                        ImmutableList<Text> children = child.getChildren();
                        for (int i = 0; i < children.size(); i++) {
                            if (!list.get(i).equalsIgnoreCase("||")) {
                                text.append(Text.builder()
                                        .format(child.getFormat())
                                        .append(Text.of(list.get(i).replaceAll("&", "§"))).build()
                                );
                            }
                        }
                    }
                    event.setMessage(text);
                }
            }
        }
    }

    @Listener
    public void onOpen(InteractInventoryEvent.Open event) {
        Set<MainEntity> entities = mainProvider.getEntities();
        for (MainEntity entity : entities) {
            Map<String, MainEntity.GuiEntity> locales = entity.getGui();
            for (String regex : locales.keySet()) {
                Container targetInventory = event.getTargetInventory();
                String name = targetInventory.getName().get();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    MainEntity.GuiEntity guiEntity = locales.get(regex);

                    // FIXME NAME It seems like there isn't a method to set the name
//                    Pattern pattern1 = Pattern.compile("(\\{\\d+})");
//                    Matcher matcher1 = pattern1.matcher(regex);
//                    int i = 1;
//                    while (matcher1.find()) {
//                        name.replaceAll(matcher1.group(1), matcher.group(i));
//                    }
//
//                    targetInventory.getName()

                    // Item
                    List<MainEntity.GuiEntity.ItemEntity> items = guiEntity.getItems();
                    Iterable<Inventory> slots = targetInventory.slots();
                    int i = 0;
                    for (Inventory slot : slots) {
                        slot.peek().ifPresent(itemStack -> {
//                            itemStack.offer(Keys.DISPLAY_NAME, );
                        });
                    }
                }
            }
        }
    }

    private ImmutableList<Text> getChildren(Text text) {
        ImmutableList.Builder<Text> children = ImmutableList.builder();
        ImmutableList<Text> textChildren = text.getChildren();
        if (textChildren.get(0).getChildren().size() != 0) {
            for (Text child : textChildren) {
                children.addAll(getChildren(child));
            }
        } else {
            children.add(text);
        }
        return children.build();
    }
}
